import App from '@src/App.jsx';
import { render } from '@testing-library/react';
import { describe, expect, it } from 'vitest';

describe('App component', () => {
  it('sets the document title to RtChats', () => {
    render(<App />);
    expect(document.title).toBe('RtChats');
  });
});
