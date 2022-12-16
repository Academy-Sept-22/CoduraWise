import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MediaType } from "../../models/MediaTypes";
import { routerPaths } from "../../routes/paths";
import useResources from "../../store/hooks/useResources";
import MediaCheckBox from "../MediaCheckBox/MediaCheckBox";
import StarRatingInput from "../StarRatingInput/StarRatingInput";
import convertTagsToArray from "./convertTagsToArray";
import "./AddResourceForm.scss";

const AddResourceForm = () => {
  const initialState = {
    title: "",
    link: "",
    tags: "",
  };
  const [formValues, setFormValues] = useState(initialState);
  const { postNewResource } = useResources();
  const [averageRating, setRating] = useState("1");
  const [checkedMediaType, setCheckedMediaType] = useState<MediaType>("video");

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
      averageRating,
      mediaType: checkedMediaType,
      tags: convertTagsToArray(formValues.tags),
    };

    postNewResource(body);
  };

  return (
    <>
      <form className="add-resource__form" onSubmit={handleSubmit}>
        <div className="add-resource__input-container">
          <label className="add-resource__label" htmlFor="title">
            Title:
          </label>
          <input
            className="add-resource__input"
            type="text"
            name="title"
            pattern="^[A-Za-z]{1}(.\s?)*"
            autoFocus
            required
            title="Requires a title"
            value={formValues.title}
            onChange={handleInputChange}
          />
        </div>
        <div className="add-resource__input-container">
          <label className="add-resource__label" htmlFor="link">
            Resource Link:
          </label>
          <input
            className="add-resource__input"
            type="text"
            name="link"
            pattern="[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)"
            autoFocus
            required
            title="Please give a valid URL to the resource"
            value={formValues.link}
            onChange={handleInputChange}
          />
        </div>
        <div className="add-resource__input-container-with-instruction">
          <div className="add-resource__input-container">
            <label className="add-resource__label" htmlFor="tags">
              Tags:
            </label>
            <input
              className="add-resource__input"
              type="text"
              name="tags"
              pattern="(#\w{3,})+"
              autoFocus
              required
              title="Please append each tag with a '#' without spaces."
              value={formValues.tags}
              onChange={handleInputChange}
            />
          </div>
          <small className="add-resource__instruction">
            Enter tags with a prepending &quot;#&quot; without spaces.
          </small>
        </div>
        <MediaCheckBox setCheckedMediaType={setCheckedMediaType} />
        <StarRatingInput setRating={setRating} />
        <input
          className="add-resource__button"
          type="submit"
          value="Submit Resource"
        />
      </form>
    </>
  );
};

export default AddResourceForm;
