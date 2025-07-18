import { z } from 'zod';

export const validateEnvConfig = {
  validator: 'standard',
  schema: {
    VITE_API_BASE_URL: z.string().url(),
  },
};
