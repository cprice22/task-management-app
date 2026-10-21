import React from "react";

interface BottomBannerProps {
  text?: string;
}

const BottomBanner: React.FC<BottomBannerProps> = ({
  text = "Made with 🩵 by Cassidy Price",
}) => {
  return (
    <div className="fixed bottom-0 left-0 right-0 bg-black text-white shadow-lg z-99 border-t">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        <p className="flex-1 text-center text-sm">
          {text}
        </p>
      </div>
    </div>
  );
};

export default BottomBanner;