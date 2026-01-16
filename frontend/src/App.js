import React, { useState } from 'react';
import StockManagement from './components/StockManagement';
import StockQuery from './components/StockQuery';
import ArticlesDepotsList from './components/ArticlesDepotsList';
import './App.css';

function App() {
  const [activeTab, setActiveTab] = useState('management');

  return (
    <div className="App">
      <div className="header">
        <h1>Gestion Stock</h1>
        <p>Stock Management System</p>
      </div>
      
      <div className="container">
        <div className="tabs">
          <button 
            className={`tab ${activeTab === 'management' ? 'active' : ''}`}
            onClick={() => setActiveTab('management')}
          >
            Stock Management
          </button>
          <button 
            className={`tab ${activeTab === 'query' ? 'active' : ''}`}
            onClick={() => setActiveTab('query')}
          >
            Stock Query
          </button>
          <button 
            className={`tab ${activeTab === 'lists' ? 'active' : ''}`}
            onClick={() => setActiveTab('lists')}
          >
            Articles & Depots
          </button>
        </div>

        <div className={`tab-content ${activeTab === 'management' ? 'active' : ''}`}>
          <StockManagement />
        </div>

        <div className={`tab-content ${activeTab === 'query' ? 'active' : ''}`}>
          <StockQuery />
        </div>

        <div className={`tab-content ${activeTab === 'lists' ? 'active' : ''}`}>
          <ArticlesDepotsList />
        </div>
      </div>
    </div>
  );
}

export default App; 