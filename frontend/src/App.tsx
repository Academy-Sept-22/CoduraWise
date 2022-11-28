import React from "react";

import "./App.scss";
import Routing from "./routes/Routing";
import ResourcesContextProvider from "./store/ResourcesContext";

function App() {
  return (<ResourcesContextProvider>
    <Routing />
  </ResourcesContextProvider>);
}

export default App;
