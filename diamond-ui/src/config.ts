// const MAINNET_RPC_URL = import.meta.env.VITE_MAINNET_RPC_URL;
// const POLYGON_RPC_URL = import.meta.env.VITE_POLYGON_RPC_URL;
// const BASE_RPC_URL = import.meta.env.VITE_BASE_RPC_URL;

// // if (MAINNET_RPC_URL === undefined)  || POLYGON_RPC_URL == undefined || BASE_RPC_URL === undefined) {
// //     throw new Error("RPC URLs for Ethereum mainnet, Polygon, and Base must be specified.");
// // }

// // Error handling
// if(MAINNET_RPC_URL === undefined) {
//     throw new Error("ETH RPC URL not configured.");
// }
// if(POLYGON_RPC_URL === undefined) {
//     throw new Error("POLYGON RPC URL not configured.");
// }
// if(BASE_RPC_URL === undefined) {
//     throw new Error("BASE RPC URL not configured.");
// }

// // Sets if the example should run locally or on chain
// export enum Chain {
//     POLYGON,
//     MAINNET,
//     BASE
//   }
  
//   // Inputs that configure this example to run
//   export interface ExampleConfig {
//     chain: Chain
//     rpc: {
//       polygon: string
//       mainnet: string
//       base: string
//     }
//   }
  
//   // Example Configuration
  
//   export const CurrentConfig: ExampleConfig = {
//     chain: Chain.MAINNET,
//     rpc: {
//       polygon: POLYGON_RPC_URL,
//       mainnet: MAINNET_RPC_URL,
//       base: BASE_RPC_URL
//     },
//   }