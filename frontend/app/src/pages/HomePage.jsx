import { useEffect } from 'react';

function HomePage() {
  useEffect(() => {
    document.title = 'RtChats';
  }, []);

  return (
    <>
      <h2>Home Page</h2>
    </>
  );
}

export default HomePage;
