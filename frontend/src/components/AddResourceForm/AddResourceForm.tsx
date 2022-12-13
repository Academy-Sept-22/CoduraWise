import React from "react";

const AddResourceForm = () => {
  return (
    <form action="">
      <label htmlFor="title">Title:</label>
      <input type="text" name="title" />
      <label htmlFor="link">Link:</label>
      <input type="text" name="link" />
      <label htmlFor="tags">Tags:</label>
      <dfn>separated by #</dfn>
      <input type="text" name="tags" />
    </form>
  );
};

export default AddResourceForm;

