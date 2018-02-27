package cinema.Model;

public enum Status {
    OK(200),
    NOT_FOUND(404);

    private int value;
    private Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
