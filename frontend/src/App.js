import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { ActivitiesPage } from './pages/ActivitiesPage';
import 'leaflet/dist/leaflet.css';
import React, { useState } from 'react';
import { SingleActivityPage } from './pages/SingleActivityPage';
import { AddNewActivityPage } from './pages/AddNewActivityPage';
import { HomePage } from './pages/HomePage';
import Login from './components/login/Login';
import useToken from './useToken';

// function setToken(userToken) {
//   sessionStorage.setItem('token', JSON.stringify(userToken));
// }

// function getToken() {
//   const tokenString = sessionStorage.getItem('token');
//   const userToken = JSON.parse(tokenString);
//   return userToken?.token
// }



function App() {
  const { token, setToken } = useToken();

  console.log("Token variable is set to: " + token);

  if(!sessionStorage.getItem('token')) {
    return <Login setToken={setToken} />
  }
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/">
            <HomePage />
          </Route>
          <Route path="/activity/:activityId">
            <SingleActivityPage />
          </Route>
          <Route exact path="/activities">
            <ActivitiesPage setToken={setToken}/>
          </Route>
          <Route path="/newactivity">
            <AddNewActivityPage />
          </Route>
        </Switch>
      </Router>
    </div>

  );
}

export default App;