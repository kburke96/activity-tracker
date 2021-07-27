import './App.css';
import {HashRouter as Router, Route, Switch} from 'react-router-dom';
import { ActivityPage } from './pages/ActivityPage';

function App() {
  return (
    <div className="App">
      <ActivityPage />
    </div>
  );
}

export default App;