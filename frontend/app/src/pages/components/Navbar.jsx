import { Box, Button, Flex, Spacer, Stack, Text } from '@chakra-ui/react';
import { Link } from 'react-router';

function Navbar() {
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
          <Button as={Link} to="/login" variant="ghost" colorScheme="blue">
            Login
          </Button>
          <Button as={Link} to="/register" variant="ghost" colorScheme="blue">
            Register
          </Button>
        </Stack>
      </Flex>
    </Box>
  );
}

export { Navbar };
