import React, { useState } from 'react';
import axios from 'axios';

const StockQuery = () => {
  const [queryData, setQueryData] = useState({
    articleId: '',
    depotId: ''
  });
  const [stockLevel, setStockLevel] = useState(null);
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setQueryData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleQueryStock = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get('/api/stock', {
        params: {
          articleId: queryData.articleId,
          depotId: queryData.depotId
        }
      });
      setStockLevel(response.data);
      setMessage('');
      setMessageType('');
    } catch (error) {
      setMessage(error.response?.data || 'Error querying stock');
      setMessageType('error');
      setStockLevel(null);
    }
  };

  return (
    <div>
      <h2>Stock Query</h2>
      
      {message && (
        <div className={`alert alert-${messageType === 'success' ? 'success' : 'error'}`}>
          {message}
        </div>
      )}

      <div className="card">
        <h3>Query Stock Level</h3>
        <form onSubmit={handleQueryStock}>
          <div className="form-group">
            <label htmlFor="queryArticleId">Article ID:</label>
            <input
              type="number"
              id="queryArticleId"
              name="articleId"
              value={queryData.articleId}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="queryDepotId">Depot ID:</label>
            <input
              type="number"
              id="queryDepotId"
              name="depotId"
              value={queryData.depotId}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <button type="submit" className="btn">
            Query Stock
          </button>
        </form>
      </div>

      {stockLevel !== null && (
        <div className="card">
          <h3>Stock Level Result</h3>
          <div className="alert alert-success">
            <strong>Current Stock Level:</strong> {stockLevel} units
          </div>
        </div>
      )}

      <div className="card">
        <h3>API Information</h3>
        <p>This interface connects to your Spring Boot backend with the following endpoints:</p>
        <ul>
          <li><strong>GET /api/stock</strong> - Query stock level by article and depot</li>
          <li><strong>POST /api/stock</strong> - Add stock with expiration date</li>
          <li><strong>PUT /api/stock</strong> - Remove stock</li>
        </ul>
      </div>
    </div>
  );
};

export default StockQuery; 