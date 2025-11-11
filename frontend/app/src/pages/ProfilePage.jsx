import { Avatar, Card } from '@chakra-ui/react';
import api from '@src/api/api.js';
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router';

function ProfilePage() {
  const { profileLink } = useParams();
  const navigate = useNavigate();
  const [profile, setProfile] = useState({});

  useEffect(() => {
    function getProfile() {
      api
        .get(`/profile/${profileLink}`)
        .then((response) => setProfile(response.data))
        .catch((error) => {
          if (error.response?.status === 404) {
            navigate('/not-found');
          }
          throw error;
        });
    }
    getProfile();
  }, [profileLink, navigate]);

  return (
    <Card.Root>
      <Card.Body gap="2">
        {profile && (
          <>
            <Avatar.Root shape="rounded" size="2xl">
              <Avatar.Fallback name={profile.displayName} />
              <Avatar.Image
                src={profile.avatar || 'https://bit.ly/sage-adebayo'}
              />
            </Avatar.Root>
            <Card.Title mt="2">{profile.displayName || 'Profile'}</Card.Title>
          </>
        )}
      </Card.Body>
    </Card.Root>
  );
}

export default ProfilePage;
