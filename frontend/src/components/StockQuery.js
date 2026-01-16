import React, { useEffect, useState } from 'react';
import { fetchArticles, fetchDepots, fetchStockLevel, fetchStockBatches } from '../api';

const StockQuery = () => {
  const [articles, setArticles] = useState([]);
  const [depots, setDepots] = useState([]);
  const [queryData, setQueryData] = useState({ articleId: '', depotId: '' });
  const [stockLevel, setStockLevel] = useState(null);
  const [batches, setBatches] = useState([]);
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');

  useEffect(() => {
    const loadRefs = async () => {
      try {
        const [arts, deps] = await Promise.all([fetchArticles(), fetchDepots()]);
        setArticles(arts);
        setDepots(deps);
      } catch (e) {
        setMessage('Failed to load reference data');
        setMessageType('error');
      }
    };
    loadRefs();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setQueryData(prev => ({ ...prev, [name]: value }));
  };

  const handleQueryStock = async (e) => {
    e.preventDefault();
    try {
      const [level, batchesRes] = await Promise.all([
        fetchStockLevel(queryData.articleId, queryData.depotId),
        fetchStockBatches(queryData.articleId, queryData.depotId)
      ]);
      setStockLevel(level);
      setBatches(batchesRes);
      setMessage('');
      setMessageType('');
    } catch (error) {
      setMessage('Error querying stock');
      setMessageType('error');
      setStockLevel(null);
      setBatches([]);
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
            <label htmlFor="queryArticleId">Article:</label>
            <select
              id="queryArticleId"
              name="articleId"
              value={queryData.articleId}
              onChange={handleInputChange}
              required
            >
              <option value="">Select article</option>
              {articles.map(a => (
                <option key={a.idArticle} value={a.idArticle}>{a.designationArticle}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="queryDepotId">Depot:</label>
            <select
              id="queryDepotId"
              name="depotId"
              value={queryData.depotId}
              onChange={handleInputChange}
              required
            >
              <option value="">Select depot</option>
              {depots.map(d => (
                <option key={d.idDepot} value={d.idDepot}>{d.nomDepot}</option>
              ))}
            </select>
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

      {batches.length > 0 && (
        <div className="card">
          <h3>Stock Batches</h3>
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
              </tr>
            </thead>
            <tbody>
              {batches.map(b => (
                <tr key={b.idStock}>
                  <td>{b.idStock}</td>
                  <td>{b.qte}</td>
                  <td>{b.datePeremption}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      <div className="card">
        <h3>API Information</h3>
        <p>This interface connects to your Spring Boot backend with the following endpoints:</p>
        <ul>
          <li><strong>GET /api/article/articles</strong> - List all articles</li>
          <li><strong>GET /api/depot/depots</strong> - List all depots</li>
          <li><strong>GET /api/stock</strong> - Query stock level by article and depot</li>
          <li><strong>GET /api/stock/list</strong> - List stock batches by article and depot</li>
          <li><strong>POST /api/stock</strong> - Add stock with expiration date</li>
          <li><strong>PUT /api/stock</strong> - Remove stock</li>
        </ul>
      </div>
    </div>
  );
};

export default StockQuery; 