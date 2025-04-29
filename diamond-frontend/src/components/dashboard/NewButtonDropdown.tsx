import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
  } from "@/components/ui/dropdown-menu"
import { IoAddOutline } from "react-icons/io5"

export const NewButtonDropdown = () => {
return(
<DropdownMenu>
  <DropdownMenuTrigger><IoAddOutline className="size-5"/></DropdownMenuTrigger>
  <DropdownMenuContent>
    <DropdownMenuLabel>Create payment</DropdownMenuLabel>
    <DropdownMenuSeparator />
    <DropdownMenuItem>Invoice</DropdownMenuItem>
    <DropdownMenuItem>Payment Link</DropdownMenuItem>
    {/* <DropdownMenuItem>Checkout Page Integration</DropdownMenuItem>
    <DropdownMenuItem>Donation</DropdownMenuItem> */}
    {/* <DropdownMenuItem>Subscription</DropdownMenuItem> */}
  </DropdownMenuContent>
</DropdownMenu>
)
}