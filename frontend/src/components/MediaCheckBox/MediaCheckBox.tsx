import React, { Dispatch, SetStateAction } from "react";
import { MediaType, MediaTypes } from "../../models/MediaTypes";

interface MediaCheckBoxProps {
  setCheckedMediaType: Dispatch<SetStateAction<string>>;
}

const MediaCheckBox = ({setCheckedMediaType} : MediaCheckBoxProps) => {
  return (
    <form className="media-selection">
      <h3>Select Media Type:</h3>
      {MediaTypes.map((mediaType: MediaType) => (
        <div key={mediaType}>
          <input
            type="checkbox"
            name={mediaType}
            id={`${mediaType}media`}
            value={mediaType}
            onChange={() => setCheckedMediaType(mediaType)}
          />
          <label htmlFor={`${mediaType}media`}>{mediaType}</label>
        </div>
      ))}
    </form>
  );
}

export default MediaCheckBox