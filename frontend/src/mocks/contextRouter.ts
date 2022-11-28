import { MemoryRouter } from "react-router-dom";
import ResourcesContextProvider from "../store/ResourcesContext";

export default function ContextRouterMock({children} : {children: JSX.Element}) {
  return (<ResourcesContextProvider>
    <MemoryRouter>
      {children}
    </MemoryRouter>
  </ResourcesContextProvider>);
};
