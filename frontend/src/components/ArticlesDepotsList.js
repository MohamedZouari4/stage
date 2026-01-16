import React, { useEffect, useState } from 'react';
import { fetchArticles, fetchDepots } from '../api';

const ArticlesDepotsList = () => {
  const [articles, setArticles] = useState([]);
  const [depots, setDepots] = useState([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');

  useEffect(() => {
    const loadData = async () => {
      try {
        setLoading(true);
        const [arts, deps] = await Promise.all([fetchArticles(), fetchDepots()]);
        setArticles(arts);
        setDepots(deps);
        setMessage('');
        setMessageType('');
      } catch (error) {
        setMessage('Failed to load articles and depots');
        setMessageType('error');
      } finally {
        setLoading(false);
      }
    };
    loadData();
  }, []);

  if (loading) {
    return (
      <div>
        <h2>Articles & Depots</h2>
        <div className="card">
          <p>Loading...</p>
        </div>
      </div>
    );
  }

  return (
    <div>
      <h2>Articles & Depots</h2>
      
      {message && (
        <div className={`alert alert-${messageType === 'success' ? 'success' : 'error'}`}>
          {message}
        </div>
      )}

      <div className="card">
        <h3>Articles ({articles.length})</h3>
        {articles.length > 0 ? (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Designation</th>
              </tr>
            </thead>
            <tbody>
              {articles.map(article => (
                <tr key={article.idArticle}>
                  <td>{article.idArticle}</td>
                  <td>{article.designationArticle}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No articles found</p>
        )}
      </div>

      <div className="card">
        <h3>Depots ({depots.length})</h3>
        {depots.length > 0 ? (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
              </tr>
            </thead>
            <tbody>
              {depots.map(depot => (
                <tr key={depot.idDepot}>
                  <td>{depot.idDepot}</td>
                  <td>{depot.nomDepot}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No depots found</p>
        )}
      </div>
    </div>
  );
};

export default ArticlesDepotsList;