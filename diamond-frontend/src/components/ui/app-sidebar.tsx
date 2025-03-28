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

 
// Menu items.
const items = [
  {
    title: "Home",
    url: "#",
    icon: Home,
  },
  {
    title: "Wallets",
    url: "#",
    icon: WalletMinimal,
  },
  {
    title: "Transactions",
    url: "#",
    icon: LiaExchangeAltSolid,
  },
  {
    title: "Customers",
    url: "#",
    icon: IoPersonOutline,
  },
  {
    title: "Payment Links",
    url: "#",
    icon: HiOutlineLink,
  },
  {
    title: "Checkout Page Integrations",
    url: "#",
    icon: MdOutlineShoppingCart,
  },
  {
    title: "Invoices",
    url: "#",
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
              {items.map((item) => (
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