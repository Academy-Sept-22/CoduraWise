import React from "react";
import { useNavigate } from "react-router-dom";
import { routerPaths } from "../../routes/paths";

const AddResourceForm = () => {
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const path = routerPaths.home;
    navigate(path);
    console.log(event.target);
  };

  return (
    <form className="add-resource__form" onSubmit={handleSubmit}>
      <label htmlFor="title">Title:</label>
      <input
        type="text"
        name="title"
        pattern="\w{3,}"
        autoFocus
        required
        title="Requires a title longer than 3 letters"
      />
      <label htmlFor="link">Resource Link:</label>
      <input
        type="text"
        name="link"
        pattern="[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)"
        autoFocus
        required
        title="Please input a URL to the resource"
      />
      <label htmlFor="tags">Tags:</label>
      <input
        type="text"
        name="tags"
        pattern="(#\w{3,})+"
        autoFocus
        required
        title="Please input tags separated by '#'"
      />
      <label htmlFor="rating">Rating:</label>
      <p>RATING TO GO HERE</p>
      <input type="submit" value="Submit Resource" />
    </form>
  );
};

export default AddResourceForm;

