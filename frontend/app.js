import React, { useReducer } from 'react';
import ReactDOM from 'react-dom';
import AppRouter from "./components/router/AppRouter";
import appReducer from './reducers/appReducer';

import 'semantic-ui-css/semantic.min.css'

export const AppDispatch = React.createContext(null);

function App() {
  const [state, dispatch] = useReducer(appReducer, {
    auth: false,
    currentUser: ''
  });

  return (
    <AppDispatch.Provider value={{dispatch, state}}>
      <AppRouter />
    </AppDispatch.Provider>
  );
}

ReactDOM.render(
  <App/>,
  document.getElementById('react')
);