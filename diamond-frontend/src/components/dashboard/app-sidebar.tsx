import {
    Sidebar,
    SidebarContent,
    SidebarGroup,
    SidebarGroupContent,
    SidebarGroupLabel,
    SidebarMenu,
    SidebarMenuButton,
    SidebarMenuItem,
  } from "@/components/ui/sidebar"

import { Home, WalletMinimal } from "lucide-react"
import { HiOutlineLink } from "react-icons/hi2";
import { LiaFileInvoiceSolid, LiaExchangeAltSolid } from "react-icons/lia";
import { IoPersonOutline } from "react-icons/io5";
import { MdOutlineShoppingCart } from "react-icons/md";
//import { AiOutlineProduct } from "react-icons/ai";

 
// Menu items.
const dashboardItems = [
  {
    title: "Home",
    url: "",
    icon: Home,
  },
  {
    title: "Wallets",
    url: "/wallets",
    icon: WalletMinimal,
  },
  {
    title: "Transactions",
    url: "/transactions",
    icon: LiaExchangeAltSolid,
  },
  {
    title: "Customers",
    url: "/customers",
    icon: IoPersonOutline,
  },
//   {
//     title: "Product Catalogue",
//     url: "/products",
//     icon: AiOutlineProduct,
//   },
]

const paymentItems = [
  {
    title: "Payment Links",
    url: "/links",
    icon: HiOutlineLink,
  },
  {
    title: "Checkout Page Integrations",
    url: "/checkouts",
    icon: MdOutlineShoppingCart,
  },
  {
    title: "Invoices",
    url: "/invoices",
    icon: LiaFileInvoiceSolid,
  },
] 
 
export function AppSidebar() {
  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel>Dashboard</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu className="md:pt-3">
              {dashboardItems.map((item) => (
                <SidebarMenuItem key={item.title}>
                  <SidebarMenuButton asChild>
                    <a href={item.url}>
                      <item.icon />
                      <span>{item.title}</span>
                    </a>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>

        <SidebarGroup>
          <SidebarGroupLabel>Payments</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu className="md:pt-3">
              {paymentItems.map((item) => (
                <SidebarMenuItem key={item.title}>
                  <SidebarMenuButton asChild>
                    <a href={item.url}>
                      <item.icon />
                      <span>{item.title}</span>
                    </a>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  )
}