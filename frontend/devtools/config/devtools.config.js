export default {
  'lint:check': ['npx', 'eslint'],
  'lint:fix': ['npx', 'eslint', '--fix'],
  'format:check': ['npx', 'prettier', '--check'],
  'format:fix': ['npx', 'prettier', '--write'],
};
