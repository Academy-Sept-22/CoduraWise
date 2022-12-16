import React from "react";
import { Dispatch, SetStateAction, useState } from "react";
import { MediaType, MediaTypes } from "../../models/MediaTypes";
import "./MediaTypeSelection.scss";

interface MediaTypeSelectionProps {
  setFilterMediaTypes: Dispatch<SetStateAction<MediaType[]>>;
}

const MediaTypeSelection = ({
  setFilterMediaTypes,
}: MediaTypeSelectionProps) => {
  const [checkedState, setCheckedState] = useState(
    new Array(MediaTypes.length).fill(false),
  );

  const createFilter = (checkedArray: Array<boolean>) => {
    const mediaTypesChecked: Array<MediaType> = [];
    checkedArray.map((value: boolean, index: number) => {
      if (value) {
        mediaTypesChecked.push(MediaTypes[index]);
      }
    });
    return mediaTypesChecked;
  };

  const handleOnChange = (mediaType: MediaType, position: number) => {
    // create new state with the checked value
    const updatedCheckedState = checkedState.map((item, index) =>
      index === position ? !item : item,
    );
    // create MediaType array from check list
    const mediaTypesChecked = createFilter(updatedCheckedState);
    // set the context
    setFilterMediaTypes(mediaTypesChecked);
    // set the state
    setCheckedState(updatedCheckedState);
  };

  return (
    <form className="media-selection">
      <legend className="media-selection__legend">Filter by</legend>
      {MediaTypes.map((mediaType: MediaType, index) => (
        <div key={mediaType}>
          <input
            className="media-selection__input"
            type="checkbox"
            name={mediaType}
            id={`${mediaType}media`}
            value={mediaType}
            onChange={() => handleOnChange(mediaType, index)}
            checked={checkedState[index]}
          />
          <label
            className="media-selection__label"
            htmlFor={`${mediaType}media`}
          >
            {mediaType}
          </label>
        </div>
      ))}
    </form>
  );
};

export default MediaTypeSelection;
