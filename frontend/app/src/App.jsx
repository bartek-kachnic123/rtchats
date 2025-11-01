import { Layout } from '@src/pages/components/Layout.jsx';
import HomePage from '@src/pages/HomePage.jsx';
import LoginPage from '@src/pages/LoginPage.jsx';
import RegisterPage from '@src/pages/RegisterPage.jsx';
import { Route, Routes } from 'react-router';

function App() {
  return (
    <>
      <Routes>
        <Route element={<Layout />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
