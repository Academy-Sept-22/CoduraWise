import React from "react";
import StarRating from "./components/StarRating/StarRating";

import Routing from "./routes/Routing";
import ResourcesContextProvider from "./store/ResourcesContext";

function App() {
  return (
    <ResourcesContextProvider>
      <Routing />
    </ResourcesContextProvider>
  );
}

export default App;
