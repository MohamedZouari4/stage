import React, { useEffect, useState } from 'react';
import { addStock, removeStock, fetchArticles, fetchDepots } from '../api';

const StockManagement = () => {
  const [articles, setArticles] = useState([]);
  const [depots, setDepots] = useState([]);
  const [formData, setFormData] = useState({
    articleId: '',
    depotId: '',
    qte: '',
    datePeremption: ''
  });
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
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleAddStock = async (e) => {
    e.preventDefault();
    try {
      await addStock({
        articleId: Number(formData.articleId),
        depotId: Number(formData.depotId),
        qte: Number(formData.qte),
        datePeremption: formData.datePeremption
      });
      setMessage('Stock added successfully');
      setMessageType('success');
      setFormData({ articleId: '', depotId: '', qte: '', datePeremption: '' });
    } catch (error) {
      setMessage(error.response?.data || 'Error adding stock');
      setMessageType('error');
    }
  };

  const handleRemoveStock = async (e) => {
    e.preventDefault();
    try {
      await removeStock({
        articleId: Number(formData.articleId),
        depotId: Number(formData.depotId),
        qte: Number(formData.qte)
      });
      setMessage('Stock removed successfully');
      setMessageType('success');
      setFormData({ articleId: '', depotId: '', qte: '', datePeremption: '' });
    } catch (error) {
      setMessage(error.response?.data || 'Error removing stock');
      setMessageType('error');
    }
  };

  return (
    <div>
      <h2>Stock Management</h2>
      
      {message && (
        <div className={`alert alert-${messageType === 'success' ? 'success' : 'error'}`}>
          {message}
        </div>
      )}

      <div className="card">
        <h3>Add Stock</h3>
        <form onSubmit={handleAddStock}>
          <div className="form-group">
            <label htmlFor="articleId">Article:</label>
            <select
              id="articleId"
              name="articleId"
              value={formData.articleId}
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
            <label htmlFor="depotId">Depot:</label>
            <select
              id="depotId"
              name="depotId"
              value={formData.depotId}
              onChange={handleInputChange}
              required
            >
              <option value="">Select depot</option>
              {depots.map(d => (
                <option key={d.idDepot} value={d.idDepot}>{d.nomDepot}</option>
              ))}
            </select>
          </div>
          
          <div className="form-group">
            <label htmlFor="qte">Quantity:</label>
            <input
              type="number"
              id="qte"
              name="qte"
              value={formData.qte}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="datePeremption">Expiration Date:</label>
            <input
              type="date"
              id="datePeremption"
              name="datePeremption"
              value={formData.datePeremption}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <button type="submit" className="btn btn-success">
            Add Stock
          </button>
        </form>
      </div>

      <div className="card">
        <h3>Remove Stock</h3>
        <form onSubmit={handleRemoveStock}>
          <div className="form-group">
            <label htmlFor="removeArticleId">Article:</label>
            <select
              id="removeArticleId"
              name="articleId"
              value={formData.articleId}
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
            <label htmlFor="removeDepotId">Depot:</label>
            <select
              id="removeDepotId"
              name="depotId"
              value={formData.depotId}
              onChange={handleInputChange}
              required
            >
              <option value="">Select depot</option>
              {depots.map(d => (
                <option key={d.idDepot} value={d.idDepot}>{d.nomDepot}</option>
              ))}
            </select>
          </div>
          
          <div className="form-group">
            <label htmlFor="removeQte">Quantity to Remove:</label>
            <input
              type="number"
              id="removeQte"
              name="qte"
              value={formData.qte}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <button type="submit" className="btn btn-danger">
            Remove Stock
          </button>
        </form>
      </div>
    </div>
  );
};

export default StockManagement; 