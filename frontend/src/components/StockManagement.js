import React, { useState } from 'react';
import axios from 'axios';

const StockManagement = () => {
  const [formData, setFormData] = useState({
    articleId: '',
    depotId: '',
    qte: '',
    datePeremption: ''
  });
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');

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
      const response = await axios.post('/api/stock', formData);
      setMessage(response.data);
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
      const response = await axios.put('/api/stock', {
        articleId: formData.articleId,
        depotId: formData.depotId,
        qte: formData.qte
      });
      setMessage(response.data);
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
            <label htmlFor="articleId">Article ID:</label>
            <input
              type="number"
              id="articleId"
              name="articleId"
              value={formData.articleId}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="depotId">Depot ID:</label>
            <input
              type="number"
              id="depotId"
              name="depotId"
              value={formData.depotId}
              onChange={handleInputChange}
              required
            />
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
            <label htmlFor="removeArticleId">Article ID:</label>
            <input
              type="number"
              id="removeArticleId"
              name="articleId"
              value={formData.articleId}
              onChange={handleInputChange}
              required
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="removeDepotId">Depot ID:</label>
            <input
              type="number"
              id="removeDepotId"
              name="depotId"
              value={formData.depotId}
              onChange={handleInputChange}
              required
            />
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