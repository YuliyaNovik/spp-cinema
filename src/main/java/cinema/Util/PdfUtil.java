package cinema.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PdfUtil {
    private static final String pathToTemplate = "./template/index.html";

    class Base64ImageProvider extends AbstractImageProvider {

        @Override
        public Image retrieve(String src) {
            int pos = src.indexOf("base64,");
            try {
                if (src.startsWith("data") && pos > 0) {
                    byte[] img = Base64.decode(src.substring(pos + 7));
                    return Image.getInstance(img);
                }
                else {
                    return Image.getInstance(src);
                }
            } catch (BadElementException | IOException ex) {
                return null;
            }
        }

        @Override
        public String getImageRootPath() {
            return null;
        }
    }

    public byte[] generate(Object[] items) {
        List<String[]> objects = getPdfItems(items);
        if (objects.isEmpty()) {
            return null;
        }

        try {
            String htmlData = getHtmlData(objects);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, buffer);
            document.open();

            CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext();
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            htmlContext.setImageProvider(new Base64ImageProvider());

            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser parser = new XMLParser(worker);
            parser.parse(new StringReader(htmlData));
            document.close();
            return buffer.toByteArray();
        } catch (DocumentException | IOException ex) {
            return null;
        }
    }

    private List<String[]> getPdfItems(Object[] items) {
        List<String[]> objects = new ArrayList<>();
        for (Object item : items) {
            objects.add(item.toString().split("[|]"));
        }
        return objects;
    }

    private String getHtmlData(List<String[]> items) throws IOException {
        String template = new String(Files.readAllBytes(Paths.get(pathToTemplate)));
        StringBuilder table = new StringBuilder();
        for (String[] strings: items) {
            table.append("<tr>");
            for (String string: strings) {
                table.append("<td>").append(string).append("</td>");
            }
            table.append("</tr>");
        }
        return template.replaceFirst("HTML", table.toString());
    }
}
