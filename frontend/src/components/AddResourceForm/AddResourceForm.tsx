import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MediaType } from "../../models/MediaTypes";
import { routerPaths } from "../../routes/paths";
import useResources from "../../store/hooks/useResources";
import MediaCheckBox from "../MediaCheckBox/MediaCheckBox";
import StarRatingInput from "../StarRatingInput/StarRatingInput";

const AddResourceForm = () => {
  const initialState = {
    title: "",
    link: "",
    tags: "",
  };

  const [formValues, setFormValues] = useState(initialState);
  const { postNewResource } = useResources();
  const [rating, setRating] = useState("1");
  const [checkedMediaType, setCheckedMediaType] = useState("")

  const navigate = useNavigate();

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const path = routerPaths.home;
    navigate(path);
    const body = {
      ...formValues,
      rating,
      mediaType: "video" as MediaType,
    };
    postNewResource(body);
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
        value={formValues.title}
        onChange={handleInputChange}
      />
      <label htmlFor="link">Resource Link:</label>
      <input
        type="text"
        name="link"
        pattern="[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)"
        autoFocus
        required
        title="Please give a valid URL to the resource"
        value={formValues.link}
        onChange={handleInputChange}
      />
      <label htmlFor="tags">Tags:</label>
      <input
        type="text"
        name="tags"
        pattern="(#\w{3,})+"
        autoFocus
        required
        title="Please append each tag with a '#'"
        value={formValues.tags}
        onChange={handleInputChange}
      />
      <small>Enter tags with a prepending &quot;#&quot;</small>
    
      <MediaCheckBox setCheckedMediaType={setCheckedMediaType} />
      <StarRatingInput setRating={setRating} />

      <input type="submit" value="Submit Resource" />
    </form>
  );
};

export default AddResourceForm;

