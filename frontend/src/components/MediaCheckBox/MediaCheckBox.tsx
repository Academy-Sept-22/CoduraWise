import React, { Dispatch, SetStateAction } from "react";
import { MediaType, MediaTypes } from "../../models/MediaTypes";
import "./MediaCheckBox.scss";

interface MediaCheckBoxProps {
  setCheckedMediaType: Dispatch<SetStateAction<MediaType>>;
}

const MediaCheckBox = ({ setCheckedMediaType }: MediaCheckBoxProps) => {
  return (
    <fieldset className="add-resource-media-selection">
      <h3 className="add-resource-media-selection__formHeading">
        Select Media Type:
      </h3>
      <div className="add-resource-media-selection__inputs">
        {MediaTypes.map((mediaType: MediaType) => (
          <div className="add-resource-media-selection__radio" key={mediaType}>
            <input
              type="radio"
              className="add-resource-media-selection__state"
              name="mediaType"
              id={`${mediaType}media`}
              value={mediaType}
              onChange={() => setCheckedMediaType(mediaType)}
              defaultChecked={mediaType === "video"}
            />
            <label
              htmlFor={`${mediaType}media`}
              className="add-resource-media-selection__media-input"
            >
              {mediaType}
            </label>
          </div>
        ))}
      </div>
    </fieldset>
  );
};

export default MediaCheckBox;

