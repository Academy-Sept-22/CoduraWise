import React, { Dispatch, SetStateAction } from "react";
import { MediaType, MediaTypes } from "../../models/MediaTypes";

interface MediaCheckBoxProps {
  setCheckedMediaType: Dispatch<SetStateAction<MediaType>>;
}

const MediaCheckBox = ({ setCheckedMediaType }: MediaCheckBoxProps) => {
  return (
    <form className="media-selection">
      <h3>Select Media Type:</h3>
      {MediaTypes.map((mediaType: MediaType) => (
        <div key={mediaType}>
          <input
            type="radio"
            name="mediaType"
            id={`${mediaType}media`}
            value={mediaType}
            onChange={() => setCheckedMediaType(mediaType)}
            defaultChecked={mediaType === "video"}
          />
          <label htmlFor={`${mediaType}media`}>{mediaType}</label>
        </div>
      ))}
    </form>
  );
};

export default MediaCheckBox;

