import React, { useContext } from "react";
import { MediaTypes } from "../../models/MediaTypes"
import { Resource } from "../../models/Resource"
import { ResourcesContext } from "../../store/ResourcesContext";

const MediaTypeSelection = () => {
  const { resources, setResources } = useContext(ResourcesContext);

  MediaTypes.map((mediaType : string) => console.log("mediaType", mediaType));

  const handleOnChange = (mediaType: string) => {
    console.log("Clicked ", mediaType);
    // filter the resources and set it to the context
    const filteredResources: Resource[] = [...resources];
    filteredResources.pop();
    setResources(filteredResources);
  }

  return(
    <form className="media-selection">
      <h3>Filter by</h3>
      {MediaTypes.map((mediaType : string) => (
        <div key={mediaType}>
          <input 
            type="checkbox" 
            name={mediaType} 
            id={`${mediaType}media`} 
            value={mediaType}
            onChange={() => handleOnChange(mediaType)} />
          <label htmlFor={`${mediaType}media`}>{mediaType}</label>
        </div>
      ))}
    </form>
  )
}

export default MediaTypeSelection;