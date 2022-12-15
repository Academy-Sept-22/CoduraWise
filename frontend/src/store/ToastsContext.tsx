import React, { createContext } from "react";
import { toast } from "react-toastify";

interface ToastFunctions {
  notifyResourceSubmit: (success: boolean) => void;
}

export default function ToastsContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const notifyResourceSubmit = (success: boolean) => {
    if (success) {
      toast.success("Congratulations! Resource Added");
    } else {
      toast.error("Error Adding Resource");
    }
  };

  return (
    <ToastsContext.Provider
      value={{
        notifyResourceSubmit,
      }}
    >
      {children}
    </ToastsContext.Provider>
  );
}
export type ContextState = ToastFunctions;
export const ToastsContext = createContext({} as ContextState);

