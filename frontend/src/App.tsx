import React from "react";

import Routing from "./routes/Routing";
import ResourcesContextProvider from "./store/ResourcesContext";
import ToastsContextProvider from "./store/ToastsContext";

function App() {
  return (
    <ResourcesContextProvider>
      <ToastsContextProvider>
        <Routing />
      </ToastsContextProvider>
    </ResourcesContextProvider>
  );
}

export default App;
