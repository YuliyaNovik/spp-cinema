export const makeMovieFilterUrl = (name?, years?, durations?): string => {
  let urlName = "";
  let urlYears = "";
  let urlDurations = "";
  let url = [];
  if (name) {
    urlName += `name=${name}*s`;
    url.push(urlName);
  }
  if (years.length) {
    urlYears = `year=${years.join(",")}*s`;
    url.push(urlYears);
  }
  if (durations.length) {
    urlDurations = `duration=${durations.join(",")}*i`;
    url.push(urlDurations);
  }

  if (!url.length) {
    return "*";
  }

  return url.join("+");
};

export const makeUserFilterUrl = (name?, role?, email?): string => {
  let urlName = "";
  let urlRole = "";
  let urlEmail = "";
  let url = [];

  if (name) {
    urlName += `name=${name}*s`;
    url.push(urlName);
  }
  if (role.length) {
    urlRole = `role=${role.join(",")}*s`;
    url.push(urlRole);
  }
  if (email.length) {
    urlEmail = `email=${email.join(",")}*i`;
    url.push(urlEmail);
  }

  if (!url.length) {
    return "*";
  }

  return url.join("+");
};
