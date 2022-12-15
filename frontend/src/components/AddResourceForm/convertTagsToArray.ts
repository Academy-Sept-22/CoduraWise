const convertTagsToArray = (tagsString: string) => {
  return tagsString.split("#").splice(1);
};

export default convertTagsToArray;
