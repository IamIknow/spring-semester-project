import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import AppRouter from "./components/router/AppRouter";

import 'semantic-ui-css/semantic.min.css'

class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <AppRouter/>
    );
  }
}

ReactDOM.render(
  <App/>,
  document.getElementById('react')
);