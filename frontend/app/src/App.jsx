import { Layout } from '@src/pages/components/Layout.jsx';
import NotFoundPage from '@src/pages/errors/NotFoundPage.jsx';
import HomePage from '@src/pages/HomePage.jsx';
import LoginPage from '@src/pages/LoginPage.jsx';
import ProfilePage from '@src/pages/ProfilePage.jsx';
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
          <Route path="/profile/:profileLink" element={<ProfilePage />} />
          <Route path="/not-found" element={<NotFoundPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
