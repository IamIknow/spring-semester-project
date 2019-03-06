import React from 'react';
import { Redirect } from "react-router-dom";
import Route from "react-router-dom/es/Route";

export default (props) => {
  const { Render } = props;

  return (<Route render={() => (
    localStorage.getItem('auth') === 'true'
      ? <Render {...props} />
      : <Redirect to='/login' />
  )} />);
}
