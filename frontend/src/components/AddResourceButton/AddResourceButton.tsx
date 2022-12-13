import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";

const AddResourceButton = () => {
  return (
    <Link to={routerPaths.addResource}>Add Resource</Link>
  )
}

export default AddResourceButton;