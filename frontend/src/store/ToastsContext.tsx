import React, { createContext } from "react";
import { toast } from "react-toastify";

interface ToastFunctions {
  notifyAddResourceSuccess: () => void;
}

export default function ToastsContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const notifyAddResourceSuccess = () => toast("Wow so easy !");

  return (
    <ToastsContext.Provider value={{ notifyAddResourceSuccess }}>
      {children}
    </ToastsContext.Provider>
  );
}
export type ContextState = ToastFunctions;
export const ToastsContext = createContext({} as ContextState);
