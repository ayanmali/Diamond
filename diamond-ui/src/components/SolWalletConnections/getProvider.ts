import { PhantomProvider } from './types';

const getProvider = (): PhantomProvider | undefined => {
    if ('phantom' in window) {
      const anyWindow = window as { phantom?: { solana: PhantomProvider } };
      const provider = anyWindow.phantom?.solana;

      if (provider) {
        return provider;
      }
    }

    window.open('https://phantom.app/', '_blank');
  };

  export default getProvider;