import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { ActivitiesPage } from './pages/ActivitiesPage';
import 'leaflet/dist/leaflet.css';
import React from 'react';
import { SingleActivityPage } from './pages/SingleActivityPage';
import { AddNewActivityPage } from './pages/AddNewActivityPage';
import { HomePage } from './pages/HomePage';

function App() {
  // const App = () => {
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
            <ActivitiesPage />
          </Route>4
          <Route path="/newactivity">
            <AddNewActivityPage />
          </Route>
        </Switch>
      </Router>
    </div>

  );
}

export default App;