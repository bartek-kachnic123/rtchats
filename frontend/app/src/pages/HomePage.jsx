import { Button } from '@chakra-ui/react';
import api from '@src/api/api.js';
import { loggedOut } from '@src/features/auth/authSlice.js';
import { useDispatch, useSelector } from 'react-redux';

function HomePage() {
  const { isAuthenticated } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const logout = async () => {
    await api.post('/logout');
    dispatch(loggedOut());
  };

  return (
    <>
      <h2>Home Page</h2>
      {isAuthenticated && (
        <div>
          <h3>Logged in</h3>
          <Button onClick={logout}>Log out</Button>
        </div>
      )}
    </>
  );
}

export default HomePage;
