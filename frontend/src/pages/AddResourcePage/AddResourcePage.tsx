import React from "react";
import AddResourceForm from "../../components/AddResourceForm/AddResourceForm";
import "./AddResourcePage.scss";

const AddResourcePage = () => {
  return (
    <section className="add-resource-page">
      <h1 className="add-resource-page__heading">Submit your Resource</h1>
      <AddResourceForm />
    </section>
  );
};

export default AddResourcePage;
