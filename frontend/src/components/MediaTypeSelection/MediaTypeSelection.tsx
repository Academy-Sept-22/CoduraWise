import React, { useContext, useState } from "react";
import { MediaType, MediaTypes } from "../../models/MediaTypes"
import { Resource } from "../../models/Resource"
import { ResourcesContext } from "../../store/ResourcesContext";

const MediaTypeSelection = () => {
  const { mediaTypes, setMediaTypes } = useContext(ResourcesContext);
  const [checkedState, setCheckedState] = useState(
    new Array(MediaTypes.length).fill(false)
);


  MediaTypes.map((mediaType : string) => console.log("mediaType", mediaType));

  const handleOnChange = (mediaType: MediaType, position : number) => {

    const updatedCheckedState = checkedState.map((item, index) =>
      index === position ? !item : item
    )

    const TypesChecked : Array<MediaType> = []

    setCheckedState(updatedCheckedState);

    checkedState.map((value : MediaType, index : number) => {
       if (value) {
        TypesChecked.push(MediaTypes[index])
       }
      }
    )
    setMediaTypes(TypesChecked)
  }


  return(
    <form className="media-selection">
      <h3>Filter by</h3>
      {MediaTypes.map((mediaType : MediaType, index) => (
        <div key={mediaType}>
          <input 
            type="checkbox" 
            name={mediaType} 
            id={`${mediaType}media`} 
            value={mediaType}
            onChange={() => handleOnChange(mediaType, index)}
            checked={checkedState[index]}
            />
          <label htmlFor={`${mediaType}media`}>{mediaType}</label>
        </div>
      ))}
    </form>
  )
}

export default MediaTypeSelection;