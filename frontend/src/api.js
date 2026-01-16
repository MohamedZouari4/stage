import axios from 'axios';

export const fetchArticles = async () => {
  const res = await axios.get('/api/article/articles');
  return res.data;
};

export const fetchDepots = async () => {
  const res = await axios.get('/api/depot/depots');
  return res.data;
};

export const fetchStockLevel = async (articleId, depotId) => {
  const res = await axios.get('/api/stock', { params: { articleId, depotId } });
  return res.data;
};

export const fetchStockBatches = async (articleId, depotId) => {
  const res = await axios.get('/api/stock/list', { params: { articleId, depotId } });
  return res.data;
};

export const addStock = async (payload) => {
  const res = await axios.post('/api/stock', payload);
  return res.data;
};

export const removeStock = async (payload) => {
  const res = await axios.put('/api/stock', payload);
  return res.data;
};