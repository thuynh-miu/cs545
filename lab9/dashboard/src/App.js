import { BrowserRouter } from 'react-router-dom';
import './App.css';
import Dashboard from './containers/Dashboard/Dashboard'

function App() {
  return (
    <div>
      <BrowserRouter>
        <h1> Welcome WAA </h1>
        <Dashboard />
      </BrowserRouter>
    </div>
  );
}

export default App;
