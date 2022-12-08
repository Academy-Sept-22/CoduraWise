import React from "react";
import { createBrowserHistory } from "history";
import ReactDOM from "react-dom/client";
import "./index.scss";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement,
);

const history = createBrowserHistory();
// 1. Set up the browser history with the updated location
// (minus the # sign)
const path = (/#!(\/.*)$/.exec(location.hash) || [])[1];

if (path) {
  history.replace(path);
}

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);

reportWebVitals();
