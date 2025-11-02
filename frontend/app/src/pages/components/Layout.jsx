import { Navbar } from '@src/pages/components/Navbar.jsx';
import { Outlet } from 'react-router';

function Layout() {
  return (
    <div>
      <Navbar />
      <main style={{ padding: '0.5rem' }}>
        <Outlet />
      </main>
    </div>
  );
}

export { Layout };
