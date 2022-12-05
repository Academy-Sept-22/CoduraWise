import React from "react";
import { MediaTypes } from "../../models/MediaTypes"

const MediaTypeSelection = () => {
  return(
    <form className="media-selection">
      {MediaTypes.map((mediaType : string) =>(
        <input key={mediaType} type="checkbox" name={mediaType} id={`${mediaType}media`} value={mediaType}/>
      ))}
    </form>
  )
}

export default MediaTypeSelection;