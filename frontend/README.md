# Gestion Stock Frontend

A React-based frontend for the Gestion Stock application.

## Features

- **Stock Management**: Add and remove stock with expiration dates
- **Stock Query**: Query current stock levels by article and depot
- **Modern UI**: Clean, responsive design with tabbed interface
- **API Integration**: Connects to Spring Boot backend

## Getting Started

### Prerequisites

- Node.js (version 14 or higher)
- npm or yarn
- Running Spring Boot backend on port 8081

### Installation

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

The application will open in your browser at `http://localhost:3000`.

### Backend Connection

The frontend is configured to connect to the Spring Boot backend running on `http://localhost:8081`. Make sure your backend is running before using the frontend.

## Usage

### Stock Management Tab

- **Add Stock**: Enter article ID, depot ID, quantity, and expiration date to add stock
- **Remove Stock**: Enter article ID, depot ID, and quantity to remove stock

### Stock Query Tab

- **Query Stock**: Enter article ID and depot ID to check current stock levels
- **API Information**: View available backend endpoints

## API Endpoints

The frontend connects to the following backend endpoints:

- `GET /api/stock` - Query stock level
- `POST /api/stock` - Add stock
- `PUT /api/stock` - Remove stock
- `DELETE /api/stock/delete/{id}` - Delete stock by ID

## Build for Production

To create a production build:

```bash
npm run build
```

This will create an optimized build in the `build` folder.

## Technologies Used

- React 18
- Axios for API calls
- CSS3 for styling
- Modern JavaScript (ES6+) 