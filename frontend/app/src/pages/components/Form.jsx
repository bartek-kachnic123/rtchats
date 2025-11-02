import { Box, Button, Heading, Text, VStack } from '@chakra-ui/react';
import { RiArrowRightLine } from 'react-icons/ri';

function Form({ title, children, onSubmit, submitLabel, loading }) {
  return (
    <Box
      w={{ base: '100%', md: '80%' }}
      display="flex"
      flexDirection={{ base: 'column', md: 'row' }}
      borderRadius="md"
      bg="white"
      border="1px solid #CED4DA"
      overflow="hidden"
      boxShadow="0 4px 12px rgba(0,0,0,0.1)"
    >
      <Box
        w={{ base: '100%', md: '30%' }}
        h={{ base: '200px', md: 'auto' }}
        bg="blue.100"
        display="flex"
        alignItems="center"
        justifyContent="center"
        borderTopRightRadius={{ base: '0', md: '200px' }}
        borderBottomRightRadius={{ base: '0', md: '200px' }}
        borderTopLeftRadius="md"
        borderBottomLeftRadius={{ base: '0', md: 'md' }}
      >
        <Text fontSize="2xl" color="blue.700">
          Image Here
        </Text>
      </Box>

      <Box w={{ base: '100%', md: '50%' }} p={10}>
        <VStack spacing={6} align="center">
          <Heading as="h2" size="xl" color="#212529">
            {title}
          </Heading>
          {children}
          <Button
            type="submit"
            width={{ base: 'full', md: '50%' }}
            size="lg"
            fontWeight="bold"
            borderRadius="md"
            bg="#007AFF"
            color="white"
            _hover={{ bg: '#006AE6' }}
            loading={loading}
            loadingText="Loading..."
            onClick={onSubmit}
          >
            {submitLabel} <RiArrowRightLine />
          </Button>
        </VStack>
      </Box>
    </Box>
  );
}

export { Form };
