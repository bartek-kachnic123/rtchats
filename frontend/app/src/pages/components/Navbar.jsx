import {
  Avatar,
  Box,
  Button,
  Card,
  Flex,
  Spacer,
  Stack,
  Text,
} from '@chakra-ui/react';
import { fetchMyProfile } from '@src/features/profile/profileSlice.js';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router';

function Navbar() {
  const dispatch = useDispatch();
  const { profile } = useSelector((state) => state.profile);
  const { isAuthenticated } = useSelector((state) => state.auth);
  console.log(profile);

  useEffect(() => {
    if (isAuthenticated) {
      dispatch(fetchMyProfile());
    }
  }, [isAuthenticated, dispatch]);

  return (
    <Box bg="white" px={8} py={4} boxShadow="sm">
      <Flex align="center" mx="auto">
        <Text fontSize="xl" fontWeight="bold" color="blue.600">
          MyApp
        </Text>
        <Spacer />
        <Stack
          direction={{ base: 'column', md: 'row' }}
          spacing={4}
          align="center"
        >
          <Button as={Link} to="/" variant="ghost" colorScheme="blue">
            Home
          </Button>
          {isAuthenticated ? (
            <>
              {profile && (
                <Button
                  as={Link}
                  to={'/profile/' + profile.profileLink}
                  variant="ghost"
                  colorScheme="blue"
                >
                  Profile
                  <Avatar.Root shape="rounded" size="sm">
                    <Avatar.Fallback name={profile.displayName} />
                    <Avatar.Image
                      src={profile.avatar || 'https://bit.ly/sage-adebayo'}
                    />
                  </Avatar.Root>
                </Button>
              )}
            </>
          ) : (
            <>
              <Button as={Link} to="/login" variant="ghost" colorScheme="blue">
                Login
              </Button>
              <Button
                as={Link}
                to="/register"
                variant="ghost"
                colorScheme="blue"
              >
                Register
              </Button>
            </>
          )}
        </Stack>
      </Flex>
    </Box>
  );
}

export { Navbar };
